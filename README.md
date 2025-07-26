# Unity Automation Dev Task

This project contains UI and API automation tests for Unity services, using Java, Maven, Selenium, and TestNG.

## 📁 Project Structure

```
src/
  └── test/
       ├── java/         # Test classes (UI, API)
       └── resources/    # Test resources (testng.xml, configs)
pom.xml                  # Maven build file
```

---

## ⚙️ Prerequisites

Make sure you have the following installed on your machine:

* **Java 17 or above**
* **Maven 3.8+**
* **Git**
* (Optional) **ChromeDriver** (for UI tests)

---

## 🚀 Clone the Repository

```bash
git clone https://github.com/gilsheps/autoamation-dev-task-unity.git
cd autoamation-dev-task-unity
```

---

## 💠 Install Dependencies

Maven will automatically install dependencies when you build the project:

```bash
mvn clean install
```

---

## ✅ Running Tests

### Run All Tests (UI + API)

```bash
mvn test
```

### Run Specific Test Suite (TestNG)

If you're using `testng.xml`, run tests with:

```bash
mvn test -DsuiteXmlFile=testng.xml
```

### Run Specific Test Class

```bash
mvn -Dtest=ClassName test
```

Replace `ClassName` with the name of your test class, e.g.:

```bash
mvn -Dtest=LoginTests test
```

---

## 📆 Build Project (Optional)

```bash
mvn package
```

---

## 🔪 Test Reports

TestNG and Maven Surefire generate reports in:

```
TestReport/
```

You can view results in `ExtentReports.html` file.

---

## 🧰 Troubleshooting

* **Ensure ChromeDriver is compatible with your Chrome version.**
* **Use `mvn clean` before re-running tests to avoid stale build issues.**
* **For failed tests, check `target/surefire-reports` for details.**

---

## 📄 License

This project is for development tasks and internal testing purposes.

---
