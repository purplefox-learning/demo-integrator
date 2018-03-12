FROM artifactory.global.standardchartered.com/edmi/edmi-java-base-image:latest

ARG artifactVersion=1.0.0-SNAPSHOT

EXPOSE 8080

RUN mkdir -p /opt/app/edmi-kube-demo-integrator/tmp
WORKDIR /opt/app/edmi-kube-demo-integrator

RUN chmod -R 777 .

RUN useradd -ms /bin/bash edmi-kube-demo-integrator
USER edmi-kube-demo-integrator

ENV JAVA_OPTS='-Djava.io.tmpdir=./tmp'

COPY build/libs/edmi-kube-demo-integrator-${artifactVersion}-exec.jar edmi-kube-demo-integrator-exec.jar

ENTRYPOINT exec java $JAVA_OPTS -jar edmi-kube-demo-integrator-exec.jar