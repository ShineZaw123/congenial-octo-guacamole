// Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
// SPDX-License-Identifier: Apache-2.0
// snippet-start:[sns.go.list_subscriptions]
package main

import (
	"github.com/aws/aws-sdk-go/aws/session"
	"github.com/aws/aws-sdk-go/service/sns"

	"fmt"
	"os"
)

func main() {
	// Initialize a session that the SDK will use to load
	// credentials from the shared credentials file. (~/.aws/credentials).
	sess := session.Must(session.NewSessionWithOptions(session.Options{
		SharedConfigState: session.SharedConfigEnable,
	}))

	svc := sns.New(sess)

	result, err := svc.ListSubscriptions(nil)
	if err != nil {
		fmt.Println(err.Error())
		os.Exit(1)
	}

	for _, s := range result.Subscriptions {
		fmt.Println(*s.SubscriptionArn)
		fmt.Println("  " + *s.TopicArn)
		fmt.Println("")
	}
}

// snippet-end:[sns.go.list_subscriptions]
