package com.mydomain;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2ClientBuilder;
import com.amazonaws.services.ec2.model.DescribeInstancesRequest;
import com.amazonaws.services.ec2.model.DescribeInstancesResult;
import com.amazonaws.services.ec2.model.Filter;
import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.Reservation;
 
import java.util.List;
 
public class CheckManagerTag {
    public static void main(String[] args) {
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
                    System.out.println("Found instance with 'manager' tag: " + instance.getInstanceId());
                }
            }
        }
    }
}