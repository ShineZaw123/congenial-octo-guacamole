// Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
// SPDX-License-Identifier: Apache-2.0

// snippet-start:[gov2.sns.Hello]

package main

import (
	"context"
	"fmt"
	"log"

	"github.com/aws/aws-sdk-go-v2/config"
	"github.com/aws/aws-sdk-go-v2/service/sns"
	"github.com/aws/aws-sdk-go-v2/service/sns/types"
)

// main uses the AWS SDK for Go V2 to create an Amazon Simple Notification Service
// (Amazon SNS) client and list the topics in your account.
// This example uses the default settings specified in your shared credentials
// and config files.
func main() {
	sdkConfig, err := config.LoadDefaultConfig(context.TODO())
	if err != nil {
		fmt.Println("Couldn't load default configuration. Have you set up your AWS account?")
		fmt.Println(err)
		return
	}
	snsClient := sns.NewFromConfig(sdkConfig)
	fmt.Println("Let's list the topics for your account.")
	var topics []types.Topic
	paginator := sns.NewListTopicsPaginator(snsClient, &sns.ListTopicsInput{})
	for paginator.HasMorePages() {
		output, err := paginator.NextPage(context.TODO())
		if err != nil {
			log.Printf("Couldn't get topics. Here's why: %v\n", err)
			break
		} else {
			topics = append(topics, output.Topics...)
		}
	}
	if len(topics) == 0 {
		fmt.Println("You don't have any topics!")
	} else {
		for _, topic := range topics {
			fmt.Printf("\t%v\n", *topic.TopicArn)
		}
	}
}

// snippet-end:[gov2.sns.Hello]
