// Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
// SPDX-License-Identifier: Apache-2.0

// snippet-start:[pinpoint.java.pinpoint_import_segment.complete]

import com.amazonaws.AmazonServiceException;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.pinpoint.AmazonPinpoint;
import com.amazonaws.services.pinpoint.AmazonPinpointClientBuilder;
import com.amazonaws.services.pinpoint.model.*;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ImportSegment {

    public static void main(String[] args) {

        final String USAGE = "\n" +
                "ImportSegment - Creates a segment by: \n" +
                "1.) Uploading the endpoint definitions that belong to the segment to an Amazon S3 bucket. \n" +
                "2.) Importing the endpoint definitions from the bucket to an Amazon Pinpoint application." +
                "    Amazon Pinpoint creates a segment that has the specified name.\n\n" +
                "Usage: ImportSegment <endpointsFileLocation> <s3BucketName> <iamImportRoleArn> <segmentName> <applicationId>\n\n"
                +
                "Where:\n" +
                "  endpointsFileLocation - The relative location of the JSON file that contains the endpoint definitions.\n"
                +
                "  s3BucketName - The name of the Amazon S3 bucket to upload the JSON file to. If the bucket doesn't " +
                "exist, a new bucket is created.\n" +
                "  iamImportRoleArn - The ARN of an IAM role that grants Amazon Pinpoint read permissions so the S3 bucket.\n"
                +
                "  segmentName - The name for the segment that you are creating or updating." +
                "  applicationId - The ID of the Amazon Pinpoint application to add the endpoints to.";

        if (args.length < 1) {
            System.out.println(USAGE);
            System.exit(1);
        }

        String endpointsFileLocation = args[0];
        String s3BucketName = args[1];
        String iamImportRoleArn = args[2];
        String segmentName = args[3];
        String applicationId = args[4];

        Path endpointsFilePath = Paths.get(endpointsFileLocation);
        File endpointsFile = new File(endpointsFilePath.toAbsolutePath().toString());
        uploadToS3(endpointsFile, s3BucketName);

        importSegment(endpointsFile.getName(), s3BucketName, iamImportRoleArn, segmentName, applicationId);

    }

    private static void uploadToS3(File endpointsFile, String s3BucketName) {

        // Initializes Amazon S3 client.
        final AmazonS3 s3 = AmazonS3ClientBuilder.defaultClient();

        // Checks whether the specified bucket exists. If not, attempts to create one.
        if (!s3.doesBucketExistV2(s3BucketName)) {
            try {
                s3.createBucket(s3BucketName);
                System.out.format("Created S3 bucket %s.\n", s3BucketName);
            } catch (AmazonS3Exception e) {
                System.err.println(e.getErrorMessage());
                System.exit(1);
            }
        }

        // Uploads the endpoints file to the bucket.
        String endpointsFileName = endpointsFile.getName();
        System.out.format("Uploading %s to S3 bucket %s . . .\n", endpointsFileName, s3BucketName);
        try {
            s3.putObject(s3BucketName, "imports/" + endpointsFileName, endpointsFile);
            System.out.println("Finished uploading to S3.");
        } catch (AmazonServiceException e) {
            System.err.println(e.getErrorMessage());
            System.exit(1);
        }
    }

    private static void importSegment(String endpointsFileName, String s3BucketName, String iamImportRoleArn,
            String segmentName, String applicationId) {

        // The S3 URL that Amazon Pinpoint requires to find the endpoints file.
        String s3Url = "s3://" + s3BucketName + "/imports/" + endpointsFileName;

        // Defines the import job that Amazon Pinpoint runs.
        ImportJobRequest importJobRequest = new ImportJobRequest()
                .withS3Url(s3Url)
                .withFormat(Format.JSON)
                .withRoleArn(iamImportRoleArn)
                .withRegisterEndpoints(true)
                .withDefineSegment(true)
                .withSegmentName(segmentName);
        CreateImportJobRequest createImportJobRequest = new CreateImportJobRequest()
                .withApplicationId(applicationId)
                .withImportJobRequest(importJobRequest);

        // Initializes the Amazon Pinpoint client.
        AmazonPinpoint pinpointClient = AmazonPinpointClientBuilder.standard()
                .withRegion(Regions.US_EAST_1).build();

        System.out.format("Creating segment %s with the endpoints in %s . . .\n", segmentName, endpointsFileName);

        try {

            // Runs the import job with Amazon Pinpoint.
            CreateImportJobResult importResult = pinpointClient.createImportJob(createImportJobRequest);
            String jobId = importResult.getImportJobResponse().getId();

            // Checks the job status until the job completes or fails.
            GetImportJobResult getImportJobResult = null;
            String jobStatus = null;
            do {
                getImportJobResult = pinpointClient.getImportJob(new GetImportJobRequest()
                        .withJobId(jobId)
                        .withApplicationId(applicationId));
                jobStatus = getImportJobResult.getImportJobResponse().getJobStatus();
                System.out.format("Import job %s . . .\n", jobStatus.toLowerCase());
                if (jobStatus.equals("FAILED")) {
                    System.err.println("Failed to import segment.");
                    System.exit(1);
                }
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    System.err.println(e.getMessage());
                    System.exit(1);
                }
            } while (!jobStatus.equals("COMPLETED"));

            System.out.println("Finished importing segment.");

            // Checks for entries that failed to import.
            List<String> failedEndpoints = getImportJobResult.getImportJobResponse().getFailures();
            if (failedEndpoints != null) {
                System.out.println("Failed to import the following entries:");
                for (String failedEndpoint : failedEndpoints) {
                    System.out.println(failedEndpoint);
                }
            }

        } catch (AmazonServiceException e) {
            System.err.println(e.getErrorMessage());
            System.exit(1);
        }

    }

}

// snippet-end:[pinpoint.java.pinpoint_import_segment.complete]
