# Use OpenJDK 17 base image (change to 21 or 11 if needed)
FROM eclipse-temurin:21-jdk

# Set working directory inside the container
WORKDIR /app

# Copy all project files into the container (excluding files in .dockerignore)
COPY . ./

# Build the Spring Boot app JAR (this runs Maven inside the container)
RUN ./mvnw clean package -DskipTests

# Run the built application (the wildcard works if only one jar is in target/)
CMD ["java", "-jar", "target/*.jar"]
