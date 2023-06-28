The 12-factor methodology is a set of principles and best practices for developing software-as-a-service (SaaS) applications that are designed to be scalable, maintainable, and portable. These principles were originally formulated by engineers at Heroku and have since become widely adopted in the software development community. Here's an explanation of each factor with examples of how they can be applied in a Spring application:

1. Codebase: Use version control and have a single codebase that can be deployed to multiple environments. In Spring, you can use Git for version control and have a single repository for your application's source code.

2. Dependencies: Explicitly declare and manage your application's dependencies. In Spring, you can use a dependency management tool like Maven or Gradle to define and manage your project's dependencies.

3. Config: Store configuration in the environment. Externalize configuration properties using Spring's `@Value` annotation or use Spring Cloud Config to centralize and manage configuration properties.

4. Backing Services: Treat backing services (databases, caches, message brokers) as attached resources. In Spring, you can use Spring Data to interact with databases and externalize connection details using environment variables or configuration files.

5. Build, Release, Run: Separate the build, release, and run stages of your application. Use a continuous integration and deployment pipeline to automate the build and release processes.

6. Processes: Execute the application as one or more stateless processes. Spring applications can be deployed as standalone JAR files or as containers using technologies like Docker.

7. Port Binding: Export services via port binding. In Spring, you can expose RESTful services using Spring MVC or create microservices using Spring Boot.

8. Concurrency: Scale out via the process model. Use technologies like Spring Cloud and container orchestration platforms (e.g., Kubernetes) to scale your application horizontally.

9. Disposability: Maximize robustness with fast startup and graceful shutdown. Design your Spring application to start up quickly and handle graceful shutdowns by closing connections and releasing resources properly.

10. Dev/Prod Parity: Keep development, staging, and production environments as similar as possible. Use tools like Docker and configuration management to ensure consistency across environments.

11. Logs: Treat logs as event streams. Use logging frameworks like Logback or Log4j and consider using log aggregation tools like ELK (Elasticsearch, Logstash, and Kibana) or Splunk.

12. Admin Processes: Run admin/management tasks as one-off processes. Use Spring's support for scheduled tasks (`@Scheduled`), batch processing, or Spring Cloud Task to handle administrative and management tasks.

By following the 12-factor principles in your Spring application, you can ensure that your application is scalable, maintainable, and easily deployable to different environments. These principles promote a modular and decoupled architecture, making it easier to scale your application, adopt new technologies, and manage the application's lifecycle efficiently.