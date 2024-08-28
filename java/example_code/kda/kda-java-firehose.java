// Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
// SPDX-License-Identifier: Apache-2.0

// snippet-start:[kinesisanalytics.java.example.firehose-sink]

package com.amazonaws.services.kinesisanalytics;

import com.amazonaws.services.kinesisanalytics.flink.connectors.config.ProducerConfigConstants;
import com.amazonaws.services.kinesisanalytics.flink.connectors.producer.FlinkKinesisFirehoseProducer;
import com.amazonaws.services.kinesisanalytics.runtime.KinesisAnalyticsRuntime;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kinesis.FlinkKinesisConsumer;
import org.apache.flink.streaming.connectors.kinesis.FlinkKinesisProducer;

import org.apache.flink.streaming.connectors.kinesis.config.ConsumerConfigConstants;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;

public class StreamingJob {

	private static final String region = "us-east-1";
	private static final String inputStreamName = "ExampleInputStream";
	private static final String outputStreamName = "ExampleOutputStream";

	private static DataStream<String> createSourceFromStaticConfig(StreamExecutionEnvironment env) {
		Properties inputProperties = new Properties();
		inputProperties.setProperty(ConsumerConfigConstants.AWS_REGION, region);
		inputProperties.setProperty(ConsumerConfigConstants.STREAM_INITIAL_POSITION, "LATEST");

		return env.addSource(new FlinkKinesisConsumer<>(inputStreamName, new SimpleStringSchema(), inputProperties));
	}

	private static DataStream<String> createSourceFromApplicationProperties(StreamExecutionEnvironment env)
			throws IOException {
		Map<String, Properties> applicationProperties = KinesisAnalyticsRuntime.getApplicationProperties();
		return env.addSource(new FlinkKinesisConsumer<>(inputStreamName, new SimpleStringSchema(),
				applicationProperties.get("ConsumerConfigProperties")));
	}

	private static FlinkKinesisFirehoseProducer<String> createFirehoseSinkFromStaticConfig() {
		/*
		 * com.amazonaws.services.kinesisanalytics.flink.connectors.config.
		 * ProducerConfigConstants
		 * lists of all of the properties that firehose sink can be configured with.
		 */

		Properties outputProperties = new Properties();
		outputProperties.setProperty(ConsumerConfigConstants.AWS_REGION, region);

		FlinkKinesisFirehoseProducer<String> sink = new FlinkKinesisFirehoseProducer<>(outputStreamName,
				new SimpleStringSchema(), outputProperties);
		ProducerConfigConstants config = new ProducerConfigConstants();
		return sink;
	}

	private static FlinkKinesisFirehoseProducer<String> createFirehoseSinkFromApplicationProperties() throws IOException {
		/*
		 * com.amazonaws.services.kinesisanalytics.flink.connectors.config.
		 * ProducerConfigConstants
		 * lists of all of the properties that firehose sink can be configured with.
		 */

		Map<String, Properties> applicationProperties = KinesisAnalyticsRuntime.getApplicationProperties();
		FlinkKinesisFirehoseProducer<String> sink = new FlinkKinesisFirehoseProducer<>(outputStreamName,
				new SimpleStringSchema(),
				applicationProperties.get("ProducerConfigProperties"));
		return sink;
	}

	public static void main(String[] args) throws Exception {
		// set up the streaming execution environment
		final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

		/*
		 * if you would like to use runtime configuration properties, uncomment the
		 * lines below
		 * DataStream<String> input = createSourceFromApplicationProperties(env);
		 */

		DataStream<String> input = createSourceFromStaticConfig(env);

		// Kinesis Firehose sink
		input.addSink(createFirehoseSinkFromStaticConfig());

		// If you would like to use runtime configuration properties, uncomment the
		// lines below
		// input.addSink(createFirehoseSinkFromApplicationProperties());

		env.execute("Flink Streaming Java API Skeleton");
	}
}

// snippet-end:[kinesisanalytics.java.example.firehose-sink]
