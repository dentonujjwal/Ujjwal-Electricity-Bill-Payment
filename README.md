# ⚡ Electricity Billing & Management System

[![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://www.java.com/)
[![MySQL](https://img.shields.io/badge/MySQL-00000F?style=for-the-badge&logo=mysql&logoColor=white)](https://www.mysql.com/)
[![Author](https://img.shields.io/badge/Author-Ujjwal%20Rai-blue?style=for-the-badge&logo=github)](https://github.com/dentonujjwal)

A comprehensive desktop-based **Electricity Billing & Management System** developed in **Java (Swing/AWT)** with a **MySQL** backend. This system automates the manual billing process, offering an intuitive graphical user interface for both **Administrators** and **Customers**.

---

## 🚀 Key Features

### 🔐 1. Authentication & Role-Based Access
- **Admin & Customer Login**: Dedicated login modes with credential verification.
- **Sign-Up Module**: New user registration linked with allocated meter numbers.
- **Dynamic Splash Screen**: Animated introductory loading screen.

### 👤 2. Customer Management (Admin)
- **New Customer Registration**: Auto-generated 6-digit meter numbers, address details, interactive state/city selector, and contact information.
- **Meter Information Configuration**: Configurable meter location, meter type (Electric/Solar), phase code, and billing categories.
- **Customer Directory**: View, filter, and search customer records by meter number or name.
- **Information Update**: Modify customer contact and location details anytime.

### 💡 3. Billing & Calculation Engine
- **Automated Bill Calculation**: Computes bill amounts dynamically based on consumed units, tariffs, meter rents, service charges, and taxes (Swachh Bharat Cess, Fixed Tax).
- **Invoice Generation**: Formats and generates official billing receipts detailing customer info, tariff rates, and final amount.
- **Deposit & Bill History**: Search and review payment statuses across different months.

### 💳 4. Bill Payment & Analysis (Customer)
- **Bill Payment Portal**: Customers can view outstanding bills and complete payments.
- **Multiple Payment Modes**: Integrated simulations for UPI (QR Code) and Net Banking.
- **Bill Consumption Analysis**: Monthly breakdown of energy usage and billing trends.
- **Email Notifications**: Integrated email notification handler using JavaMail API.

---

## 🛠️ Technology Stack

- **Programming Language**: Java (JDK 8 / 11+)
- **UI Framework**: Java Swing & AWT
- **Database**: MySQL Server
- **Connectivity**: Java Database Connectivity (JDBC)
- **Libraries**:
  - `activation.jar` (JavaBeans Activation Framework)
  - `javax.mail.jar` (JavaMail API for email alerts)
  - `mysql-connector-java` (JDBC Driver)

---

## 📂 Project Structure

```plaintext
Electricity-Billing-System/
├── database/
│   └── Bill_system.sql        # MySQL database schema & baseline data
├── lib/
│   ├── activation.jar         # JavaBeans Activation framework
│   └── javax.mail.jar         # JavaMail API library
├── src/
│   ├── META-INF/
│   │   └── MANIFEST.MF
│   ├── electricity/billing/system/
│   │   ├── Login.java                # Authentication login screen
│   │   ├── Signup.java               # New user registration screen
│   │   ├── Splash.java               # Startup splash screen
│   │   ├── main_class.java           # Main navigation dashboard
│   │   ├── database.java             # JDBC MySQL connection handler
│   │   ├── newCustomer.java          # Add new customer module
│   │   ├── customer_details.java     # View & search customer table
│   │   ├── deposit_details.java      # Deposit & payment records table
│   │   ├── calculate_bill.java       # Bill computation module
│   │   ├── generate_bill.java        # Bill receipt generation
│   │   ├── view_information.java     # View customer profile
│   │   ├── update_information.java   # Edit customer information
│   │   ├── bill_details.java         # Customer past bills view
│   │   ├── pay_bill.java             # Customer pay bill window
│   │   ├── payment_bill.java         # Payment gateway & QR screen
│   │   ├── bill_analysis.java        # Usage analysis module
│   │   ├── meterInfo.java            # Meter specification setup
│   │   └── NotificationHandler.java  # Email notification sender
│   └── icon/                         # UI icons, logos, and graphics
└── README.md                         # Project documentation
```

---

## ⚙️ Installation & Setup

### 1. Prerequisites
- **Java Development Kit (JDK 8 or higher)** installed on your machine.
- **MySQL Server** installed and running on port `3306`.
- Any Java IDE (IntelliJ IDEA, Eclipse, or NetBeans) or command line.

### 2. Database Configuration
1. Open **MySQL Workbench** or your terminal.
2. Run the script provided in [`database/Bill_system.sql`](database/Bill_system.sql):
   ```sql
   source /path/to/database/Bill_system.sql;
   ```
3. Update your MySQL credentials if necessary in [`src/electricity/billing/system/database.java`](src/electricity/billing/system/database.java):
   ```java
   connection = DriverManager.getConnection(
       "jdbc:mysql://localhost:3306/Bill_system",
       "root",
       "YOUR_PASSWORD"
   );
   ```

### 3. Running the Project
#### Using IntelliJ IDEA:
1. Clone or open the project folder in IntelliJ IDEA.
2. Add external JARs from the `lib/` directory:
   - Go to **File** → **Project Structure** → **Libraries** → **Add (+)**.
   - Select the JARs in the `lib/` directory as well as your `mysql-connector-java.jar`.
3. Locate `src/electricity/billing/system/Splash.java` or `Login.java`.
4. Right-click and select **Run**.

---

## 👨‍💻 Author

**Ujjwal Rai**
- GitHub: [@dentonujjwal](https://github.com/dentonujjwal)
- Email: [ujjwailrai@gmail.com](mailto:ujjwailrai@gmail.com)

---

## 📄 License
This project is open-source and available under the [MIT License](LICENSE).
