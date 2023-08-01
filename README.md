# WFLY-18300_reproducer
A simple reproducer for the WildFly Bug WFLY-18300.

It uses the latest available WildFly version `29.0.0.Final`.

Note: You also need docker to run the reproducer.

## How to run the reproducer
1) Build everything by running `mvn clean install`
2) Start the otel-collector with: `sh start-collector.sh` 
3) Then you can run the server with: `java -jar server/target/server-local-SNAPSHOT-bootable.jar`
4) Next you have to run the client once to load the metrics from the collector with: `java client/src/main/java/com/nts/reproducer/client/Main.java`
5) In the log of the collector you can find the error messages: `sh get-collector-logs.sh`

## Description

The reproducer just builds an almost bare WildFly, containing the micrometer subsystem. 
The subsystem is configured to publish metrics to an otel-collector, which has to be started beforehand.
The client just loads the metrics, which causes the ones with the diverging descriptions to be logged in the collectors log.
The error is e.g.:

```
2023-08-01T09:26:58.958Z        error   prometheusexporter@v0.82.0/log.go:23    error gathering metrics: collected metric undertow_request_count label:{name:"app"  value:"server-local-SNAPSHOT.war"}  label:{name:"deployment"  value:"server-local-SNAPSHOT.war"}
  label:{name:"job"  value:"wildfly"}  label:{name:"name"  value:"com.nts.reproducer.server.RestApplication"}  label:{name:"subdeployment"  value:"server-local-SNAPSHOT.war"}  label:{name:"type"  value:"servlet"}  counter:{value:0} has help "Number of all requests" but should have "The number of requests this listener has served"
        {"kind": "exporter", "data_type": "metrics", "name": "prometheus"}
github.com/open-telemetry/opentelemetry-collector-contrib/exporter/prometheusexporter.(*promLogger).Println
        github.com/open-telemetry/opentelemetry-collector-contrib/exporter/prometheusexporter@v0.82.0/log.go:23
github.com/prometheus/client_golang/prometheus/promhttp.HandlerForTransactional.func1
        github.com/prometheus/client_golang@v1.16.0/prometheus/promhttp/http.go:144
net/http.HandlerFunc.ServeHTTP
        net/http/server.go:2122
net/http.(*ServeMux).ServeHTTP
        net/http/server.go:2500
go.opentelemetry.io/collector/config/confighttp.(*decompressor).ServeHTTP
        go.opentelemetry.io/collector/config/confighttp@v0.82.0/compression.go:147
go.opentelemetry.io/contrib/instrumentation/net/http/otelhttp.(*Handler).ServeHTTP
        go.opentelemetry.io/contrib/instrumentation/net/http/otelhttp@v0.42.0/handler.go:212
go.opentelemetry.io/collector/config/confighttp.(*clientInfoHandler).ServeHTTP
        go.opentelemetry.io/collector/config/confighttp@v0.82.0/clientinfohandler.go:28
net/http.serverHandler.ServeHTTP
        net/http/server.go:2936
net/http.(*conn).serve
        net/http/server.go:1995
```