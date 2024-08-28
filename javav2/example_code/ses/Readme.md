# Amazon SES v2 API code examples for the SDK for Java 2.x

## Overview

Shows how to use the AWS SDK for Java 2.x to work with Amazon Simple Email Service v2 API.

<!--custom.overview.start-->
<!--custom.overview.end-->

_Amazon SES v2 API is a reliable, scalable, and cost-effective email service._

## ⚠ Important

* Running this code might result in charges to your AWS account. For more details, see [AWS Pricing](https://aws.amazon.com/pricing/) and [Free Tier](https://aws.amazon.com/free/).
* Running the tests might result in charges to your AWS account.
* We recommend that you grant your code least privilege. At most, grant only the minimum permissions required to perform the task. For more information, see [Grant least privilege](https://docs.aws.amazon.com/IAM/latest/UserGuide/best-practices.html#grant-least-privilege).
* This code is not tested in every AWS Region. For more information, see [AWS Regional Services](https://aws.amazon.com/about-aws/global-infrastructure/regional-product-services).

<!--custom.important.start-->
<!--custom.important.end-->

## Code examples

### Prerequisites

For prerequisites, see the [README](../../README.md#Prerequisites) in the `javav2` folder.


<!--custom.prerequisites.start-->
<!--custom.prerequisites.end-->

### Single actions

Code excerpts that show you how to call individual service functions.

- [Create a contact in a contact list](src/main/java/com/example/sesv2/NewsletterWorkflow.java#L204) (`CreateContact`)
- [Create a contact list](src/main/java/com/example/sesv2/NewsletterWorkflow.java#L112) (`CreateContactList`)
- [Create an email identity](src/main/java/com/example/sesv2/NewsletterWorkflow.java#L90) (`CreateEmailIdentity`)
- [Create an email template](src/main/java/com/example/sesv2/NewsletterWorkflow.java#L132) (`CreateEmailTemplate`)
- [Delete a  contact list](src/main/java/com/example/sesv2/NewsletterWorkflow.java#L338) (`DeleteContactList`)
- [Delete an email identity](src/main/java/com/example/sesv2/NewsletterWorkflow.java#L380) (`DeleteEmailIdentity`)
- [Delete an email template](src/main/java/com/example/sesv2/NewsletterWorkflow.java#L357) (`DeleteEmailTemplate`)
- [List the contacts in a contact list](src/main/java/com/example/sesv2/NewsletterWorkflow.java#L258) (`ListContacts`)
- [Send a simple email](src/main/java/com/example/sesv2/SendEmail.java#L6) (`SendEmail`)
- [Send a templated email](src/main/java/com/example/sesv2/NewsletterWorkflow.java#L278) (`SendEmail`)

### Scenarios

Code examples that show you how to accomplish a specific task by calling multiple
functions within the same service.

- [Newsletter workflow](src/main/java/com/example/sesv2/NewsletterWorkflow.java)


<!--custom.examples.start-->
<!--custom.examples.end-->

## Run the examples

### Instructions


<!--custom.instructions.start-->

#### SESv2 Newsletter Workflow

Review the usage instructions in [`workflows/sesv2_weekly_mailer/README.md`](../../../workflows/sesv2_weekly_mailer/README.md).

To run the Newsletter example, copy the files from workflows/sesv2_weekly_mailer/resources into a new folder, javav2/example_code/ses/resources/coupon_newsletter.

<!--custom.instructions.end-->



#### Newsletter workflow

This example shows you how to Amazon SES v2 API newsletter workflow.


<!--custom.scenario_prereqs.sesv2_NewsletterWorkflow.start-->
<!--custom.scenario_prereqs.sesv2_NewsletterWorkflow.end-->


<!--custom.scenarios.sesv2_NewsletterWorkflow.start-->
<!--custom.scenarios.sesv2_NewsletterWorkflow.end-->

### Tests

⚠ Running tests might result in charges to your AWS account.


To find instructions for running these tests, see the [README](../../README.md#Tests)
in the `javav2` folder.



<!--custom.tests.start-->
<!--custom.tests.end-->

## Additional resources

- [Amazon SES v2 API Developer Guide](https://docs.aws.amazon.com/ses/latest/dg/Welcome.html)
- [Amazon SES v2 API API Reference](https://docs.aws.amazon.com/ses/latest/APIReference-V2/Welcome.html)
- [SDK for Java 2.x Amazon SES v2 API reference](https://sdk.amazonaws.com/java/api/latest/software/amazon/awssdk/services/ses/package-summary.html)

<!--custom.resources.start-->
<!--custom.resources.end-->

---

Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.

SPDX-License-Identifier: Apache-2.0