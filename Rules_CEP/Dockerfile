FROM gateway/base

LABEL maintainer "Michael Fitzurka <michael.fitzurka@dlt.com>"

USER root

ENV APPL=rules_cep-jar-with-dependencies.jar

# transfer run-script
ADD Docker_Files/scripts/run.sh $HOME/

# make 'gateway' the owner
RUN chown -R gateway $HOME/run.sh 

# switch to gateway
USER gateway

WORKDIR $HOME/

CMD $HOME/run.sh

# Build command -> docker build --rm -t workspace_iotdatacentermonitor .
# run command -> docker run -h iotcontrollerrouter -d workspacefuse_iotdatacentermonitor
# run and connect -> docker run -P -i -t -h iotdatacentermonitor workspacefuse_iotdatacentermonitor /bin/bash
