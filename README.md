# FestOrg - Event Management Backend

FestOrg is a robust Spring Boot backend application designed to handle event management operations. It includes features for data persistence, email notifications, and payment processing integration.

## 🚀 Tech Stack

*   **Java**: 17 (Eclipse Temurin)
*   **Framework**: Spring Boot 3.5.3
*   **Build Tool**: Maven 3.9
*   **Database**: MySQL
*   **Payments**: Razorpay Integration
*   **Utilities**: Lombok, Apache POI (Excel handling), Java Mail Sender

## 🛠️ Prerequisites

*   Java Development Kit (JDK) 17
*   Maven 3.x
*   Docker
*   MySQL Server

## ⚙️ Configuration

The application uses `application.properties` for configuration. You can override these defaults using environment variables.

| Property | Env Variable | Default | Description |
| :--- | :--- | :--- | :--- |
| Server Port | `SERVER_PORT` | `3048` | Port the app runs on |
| DB URL | `SPRING_DATASOURCE_URL` | `jdbc:mysql://mysql:3306/festorg` | MySQL Connection URL |
| DB Username | `SPRING_DATASOURCE_USERNAME` | `root` | Database User |
| DB Password | `SPRING_DATASOURCE_PASSWORD` | *(set your password)* | Database Password |
| Mail User | `SPRING_MAIL_USERNAME` | *(set your email)* | Gmail Account |
| Mail Pass | `SPRING_MAIL_PASSWORD` | *(set app password)* | Gmail App Password |
| Razorpay Key | `RAZORPAY_KEY` | *(set key)* | Payment Gateway Key |
| Razorpay Secret | `RAZORPAY_SECRET` | *(set secret)* | Payment Gateway Secret |

## 🏗️ Build & Run

### Local Development
1.  **Clone the repo**:
    ```bash
    git clone https://github.com/krishnarane2005/java-maven-backend.git
    cd java-maven-backend
    ```
2.  **Build the JAR**:
    ```bash
    ./mvnw clean package
    ```
3.  **Run the App**:
    ```bash
    java -jar target/FestOrg-0.0.1-SNAPSHOT.jar
    ```

### Docker
The project includes a `Dockerfile` for containerization.

```bash
# Build Image
docker build -t festorg-backend .

# Run Container
docker run -p 3048:3048 festorg-backend
```

## 🔄 CI/CD Pipeline (Jenkins)

This project is equipped with a `Jenkinsfile` for automated delivery.

**Pipeline Stages:**
1.  **Version Update**: Automatically increments the project version using `mvn build-helper:parse-version`.
2.  **Init**: Loads helper scripts (`script.groovy`).
3.  **Build Jar**: Compiles code and packages it into a JAR.
4.  **Build Image**: Builds a Docker image tagged with the new version (e.g., `1.0.1-5`) and pushes it to Docker Hub (`krishnarane2005/java-maven-repo`).
5.  **Deploy**: (Placeholder) Triggers deployment logic.

> **Note**: The pipeline requires a Jenkins credential `docker-hub-repo` for pushing images.
