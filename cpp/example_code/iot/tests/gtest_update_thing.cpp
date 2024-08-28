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
#include "iot_samples.h"
#include "iot_gtests.h"

namespace AwsDocTest {
    // NOLINTNEXTLINE(readability-named-parameter)
    TEST_F(IoT_GTests, update_thing_3_) {
        MockHTTP mockHttp;
        bool result = mockHttp.addResponseWithBody("mock_input/empty_response.json");
        ASSERT_TRUE(result) << preconditionError() << std::endl;

        result = AwsDoc::IoT::updateThing("cpp_test_thing", {{"key", "value"}},
                                          *s_clientConfig);
        ASSERT_TRUE(result);
    }

} // namespace AwsDocTest
