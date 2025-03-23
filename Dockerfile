FROM flink:1.17.2-scala_2.12

# Copy your custom configuration into the Flink configuration directory
COPY flink-conf.yaml $FLINK_HOME/conf/
