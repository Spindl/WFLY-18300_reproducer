docker run -d \
    --rm \
    --name otel-collector \
    -p 0.0.0.0:4318:4318 \
    -p 0.0.0.0:9991:9991 \
    -v otel-collector.yaml:/etc/otel-collector.yaml \
    otel/opentelemetry-collector --config=etc/otel-collector.yaml