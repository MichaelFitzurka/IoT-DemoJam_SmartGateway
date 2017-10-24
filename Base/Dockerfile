FROM resin/rpi-raspbian:jessie

LABEL maintainer "Michael Fitzurka <michael.fitzurka@dlt.com>"

# Update the system and Install necessary RPMs
RUN apt-get update && \
    apt-get install apt-utils unzip && \
    apt-get upgrade && \
    apt-get autoremove && \
    apt-get autoclean

# Make sure required environments are set
ENV JAVA_HOME=/opt/jdk1.8.0_131/ \
    HOME=/home/gateway \
    MAVEN_HOME=/opt/apache-maven-3.5.0

# Set root password
RUN echo "root:iotdemo" | /usr/sbin/chpasswd

# copy java distribution
ADD Docker_Files/software/jdk-8u131-linux-arm32-vfp-hflt.tar.gz /opt/

RUN update-alternatives --install /usr/bin/java java /opt/jdk1.8.0_131/bin/java 1 && \
    update-alternatives --install /usr/bin/javac javac /opt/jdk1.8.0_131/bin/javac 1 && \
    update-alternatives --set java /opt/jdk1.8.0_131/bin/java && \
    update-alternatives --set javac /opt/jdk1.8.0_131/bin/javac

# copy maven distribution
ADD Docker_Files/software/apache-maven-3.5.0-bin.tar.gz /opt/

# download and install maven
RUN echo export M2_HOME=/opt/apache-maven-3.5.0 > /etc/profile.d/maven.sh && \
    sed -i '$a export PATH=$PATH:$M2_HOME/bin' /etc/profile.d/maven.sh

# Create user to run processed
RUN useradd -m -d /home/gateway -p iotdemo gateway

# Switch to new user
USER gateway

# create a few needed directories
RUN mkdir $HOME/tmp && \
    mkdir $HOME/.m2

# transfer maven configuration file
ADD config/settings.xml $HOME/.m2/

# as ADD always uses root as user, we have to give
# the file back to 'gateway'
USER root
RUN chown -R gateway $HOME/.m2/*
USER gateway

# lets create a volume to mount the external maven repo
VOLUME /maven_repo

# make $HOME/tmp the active directory
WORKDIR $HOME/tmp

# Build command -> docker build --rm -t gateway/base .
# run command -> docker run -d gateway/base
# run command -> docker run -i -t -v /home/gateway/.m2/repository/:/maven_repo  gateway/base /bin/bash
