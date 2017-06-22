#!/bin/bash

sleep 5

for file in $CONFIG_DIR/*."${CONFIG_FORMAT:-yml}"
do
	  filename=$(basename $file)
	  app=${filename%%.*}
	  curl  --output /dev/null -sX PUT --data-binary @$file http://$CONSUL_URL:$CONSUL_PORT/v1/kv/config/$app/data
done
