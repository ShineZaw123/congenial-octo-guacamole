// Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
// SPDX-License-Identifier: Apache-2.0
/*
 * Test types are indicated by the test label ending.
 *
 * _1_ Requires credentials, permissions, and AWS resources.
 * _2_ Requires credentials and permissions.
 * _3_ Does not require credentials.
 *
 */

#include <gtest/gtest.h>
#include <fstream>
#include "dynamodb_gtests.h"
#include "dynamodb_samples.h"

namespace AwsDocTest {
    // NOLINTNEXTLINE (readability-named-parameter)
    TEST_F(DynamoDB_GTests, update_item_2_) {
        bool result = createSimpleTable();
        ASSERT_TRUE(result) << preconditionError();
        const std::vector<Aws::String> keys = {SIMPLE_PRIMARY_KEY, "second_key"};
        std::vector<Aws::String> values = {"primary_value", "second_value"};
        result = putItem(SIMPLE_TABLE_NAME, keys, values);
        ASSERT_TRUE(result) << preconditionError();

        values[1] = "new_value";
        result = AwsDoc::DynamoDB::updateItem(SIMPLE_TABLE_NAME, keys[0],
                                              values[0], keys[1], values[1],
                                              *s_clientConfig);
        ASSERT_TRUE(result);
    }
} // AwsDocTest