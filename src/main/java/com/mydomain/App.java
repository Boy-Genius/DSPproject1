package com.mydomain;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CreateBucketRequest;
import com.amazonaws.services.s3.model.Bucket;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");
         // Initialize the Amazon S3 client
         AmazonS3 s3Client = AmazonS3ClientBuilder.standard().build();

         // Specify your bucket name (must be globally unique)
         String bucketName = "fuckshitmyfuckbitch";
 
         // Create a CreateBucketRequest
         CreateBucketRequest createBucketRequest = new CreateBucketRequest(bucketName);
 
         // Create the bucket
         try {
             Bucket bucket = s3Client.createBucket(createBucketRequest);
             System.out.println("Bucket created successfully: " + bucket.getName());
         } catch (Exception e) {
             System.out.println("Error creating bucket: " + e.getMessage());
         }
    }
}
