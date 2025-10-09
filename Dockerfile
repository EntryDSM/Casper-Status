FROM ubuntu:22.04

RUN apt-get update && apt-get install -y \
    openjdk-17-jdk \
    wget \
    curl \
    && rm -rf /var/lib/apt/lists/*

WORKDIR /app

ENV GRADLE_OPTS="-Dorg.gradle.daemon=false -Dorg.gradle.parallel=true -Dorg.gradle.configureondemand=true -Dorg.gradle.caching=true -Dorg.gradle.configuration-cache=true -Dorg.gradle.unsafe.configuration-cache=true -Dorg.gradle.unsafe.configuration-cache-problems=warn -Dorg.gradle.workers.max=4 -Dorg.gradle.logging.level=lifecycle -XX:+UseContainerSupport -XX:MaxRAMPercentage=75.0 -XX:+UseG1GC -XX:G1HeapRegionSize=16m -XX:+UseStringDeduplication -XX:-UsePerfData -XX:+DisableExplicitGC -XX:MaxMetaspaceSize=512m -Dkotlin.compiler.execution.strategy=in-process -Dkotlin.incremental=false -Dkotlin.daemon.jvm.options=-Xmx1g -Dkotlin.parallel.tasks.in.project=true -Dfile.encoding=UTF-8 -Duser.country=US -Duser.language=en -Duser.timezone=UTC -Djava.awt.headless=true -Djava.security.egd=file:/dev/./urandom -Dorg.gradle.internal.http.connectionTimeout=60000 -Dorg.gradle.internal.http.socketTimeout=60000 -Dorg.gradle.internal.repository.max.tentatives=1 -Dorg.gradle.internal.repository.initial.backoff=500 -Dorg.gradle.internal.network.retry.max.times=2"

ENV GRADLE_USER_HOME="/cache/.gradle"
ENV TZ=Asia/Seoul
ENV JAVA_OPTS="-XX:+UseContainerSupport -XX:MaxRAMPercentage=75.0 -XX:+UseG1GC -XX:G1HeapRegionSize=16m -XX:+UseStringDeduplication -XX:-UsePerfData"

RUN mkdir -p /cache/.gradle

COPY ./build.gradle* ./settings.gradle* ./gradle.properties* ./gradlew* ./
COPY ./gradle gradle

RUN chmod +x gradlew && ./gradlew --version
RUN chmod +x gradlew && ./gradlew dependencies || true

COPY ./ .
COPY ./ /app/source-backup
COPY ./ /app/source-backup-2
COPY ./ /app/source-backup-3
COPY ./ /app/source-backup-4
COPY ./gradle /app/gradle-backup

RUN chmod +x gradlew && ./gradlew bootJar -x test

RUN cp -r /cache/.gradle /app/.gradle-cache
RUN cp -r /cache/.gradle /app/.gradle-cache-2
RUN cp -r /cache/.gradle /app/.gradle-cache-3
RUN cp -r /cache/.gradle /app/.gradle-cache-4
RUN cp -r /app/.gradle /app/.gradle-build
RUN cp -r /app/.gradle /app/.gradle-build-2
RUN cp -r /app/.gradle /app/.gradle-build-3
RUN cp -r /app/build /app/build-backup
RUN cp -r /app/build /app/build-backup-2

RUN find /app/casper-status/build/libs -name "*.jar" ! -name "*-plain.jar" -exec cp {} /app/app.jar \;

RUN groupadd -g 1001 appgroup && useradd -u 1001 -g appgroup -s /bin/sh -m appuser
RUN chown -R appuser:appgroup /app

USER appuser

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar /app/app.jar"]