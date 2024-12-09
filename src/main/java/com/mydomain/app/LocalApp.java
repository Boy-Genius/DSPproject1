package com.mydomain.app;
import java.util.List;

import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2ClientBuilder;
import com.amazonaws.services.ec2.model.DescribeInstancesRequest;
import com.amazonaws.services.ec2.model.DescribeInstancesResult;
import com.amazonaws.services.ec2.model.Filter;
import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.Reservation;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CreateBucketRequest;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2ClientBuilder;
import com.amazonaws.services.ec2.model.DescribeInstancesRequest;
import com.amazonaws.services.ec2.model.DescribeInstancesResult;
import com.amazonaws.services.ec2.model.Filter;
import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.Reservation;
 
import java.util.List;
 
/**
 * Hello world!
 */
public class LocalApp {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        System.out.println("Hello World!");
         // Initialize the Amazon S3 client
         AmazonS3 s3Client = AmazonS3ClientBuilder.standard().build();

      
    }
    public static boolean checkManagerTag(){
        boolean res= false;
             // Create an EC2 client
        AmazonEC2 ec2 = AmazonEC2ClientBuilder.standard().build();
        
        // Filter for instances with the tag "manager"
        Filter tagFilter = new Filter()
                .withName("tag:manager")
                .withValues("true"); // Assuming "true" is the tag value, change as needed
 
        // Create a describe instances request
        DescribeInstancesRequest request = new DescribeInstancesRequest().withFilters(tagFilter);
 
        // Execute the request
        DescribeInstancesResult result = ec2.describeInstances(request);
 
        // Check if there are instances
        List<Reservation> reservations = result.getReservations();
        if (reservations.isEmpty()) {

            System.out.println("No instances found with the 'manager' tag.");
        } else {
            for (Reservation reservation : reservations) {
                for (Instance instance : reservation.getInstances()) {
                    res = true;
                    System.out.println("Found instance with 'manager' tag: " + instance.getInstanceId());
                }
            }
        }
        return res;

    }

    public static void createBucket(AmazonS3 s3Client){
           // Specify your bucket name (must be globally unique)
           String bucketName = "dspproject1";
 
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
