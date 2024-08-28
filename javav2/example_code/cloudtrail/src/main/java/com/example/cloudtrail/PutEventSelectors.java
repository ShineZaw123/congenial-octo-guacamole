// Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
// SPDX-License-Identifier: Apache-2.0

package com.example.cloudtrail;

// snippet-start:[cloudtrail.java2._selectors.main]
// snippet-start:[cloudtrail.java2._selectors.import]
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.cloudtrail.CloudTrailClient;
import software.amazon.awssdk.services.cloudtrail.model.CloudTrailException;
import software.amazon.awssdk.services.cloudtrail.model.PutEventSelectorsRequest;
import software.amazon.awssdk.services.cloudtrail.model.EventSelector;
import java.util.ArrayList;
import java.util.List;
// snippet-end:[cloudtrail.java2._selectors.import]

/**
 * Before running this Java V2 code example, set up your development
 * environment, including your credentials.
 *
 * For more information, see the following documentation topic:
 *
 * https://docs.aws.amazon.com/sdk-for-java/latest/developer-guide/get-started.html
 */

public class PutEventSelectors {

    public static void main(String[] args) {

        final String usage = """

                Usage:
                    <trailName>\s

                Where:
                    trailName - The name of the trail.\s
                """;

        if (args.length != 1) {
            System.out.println(usage);
            System.exit(1);
        }

        String trailName = args[0];
        Region region = Region.US_EAST_1;
        CloudTrailClient cloudTrailClient = CloudTrailClient.builder()
                .region(region)
                .build();

        setSelector(cloudTrailClient, trailName);
        cloudTrailClient.close();
    }

    public static void setSelector(CloudTrailClient cloudTrailClientClient, String trailName) {
        try {
            EventSelector selector = EventSelector.builder()
                    .readWriteType("All")
                    .build();

            List<EventSelector> selList = new ArrayList<>();
            selList.add(selector);
            PutEventSelectorsRequest selectorsRequest = PutEventSelectorsRequest.builder()
                    .trailName(trailName)
                    .eventSelectors(selList)
                    .build();

            cloudTrailClientClient.putEventSelectors(selectorsRequest);

        } catch (CloudTrailException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }
}
// snippet-end:[cloudtrail.java2._selectors.main]
