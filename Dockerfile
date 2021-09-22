FROM openjdk:11
#EXPOSE 9092
ADD target/blog-api.jar blog-api.jar
ENTRYPOINT ["java","-jar","blog-api.jar"]