# Parlor Management System

A **Java Swing-based desktop application** for managing customer data and services in a parlor environment.
The system includes user registration, login, treatment suggestions, admin functionalities, and a modern dark-themed interface.

---

## 🚀 Features

### 🌟 Start Page
- User-friendly interface with **Login** and **Register** options.

### 👤 User Module
- **Registration**: Enter name, date of birth, phone, username, password, and select skin tone.
- **Login**: Secure authentication using credentials saved in `users.txt`.
- **Dashboard**:
  - Displays user details and treatment history.
  - Suggests facial treatments based on **skin tone**.
  - Shows actual **images** of services.
  - Allows updates to personal info and password.
  - Dark navy-blue themed UI for a modern look.

### 🛠 Admin Module
- Admin credentials stored in `admins.txt`.
- **Admin Dashboard**:
  - View all registered users in a table format.
  - Add new customers (auto-generates user login).
  - View individual customer details.
  - **Search users** by name.
  - Pastel pink & purple themed UI for a stylish, professional look.

---

## 🎨 UI Highlights

- **Dark Mode UI** with navy/dark blue theme.
- Stylish **girlish color theme** in Admin Dashboard.
- Uses **Poppins font** and professional icons/logos.
- Enhanced button visibility and consistent layout.
- Lighter tables for readability.
- All windows are full-screen with smooth navigation.

---

## 📁 Project Structure
ParlorManagementSystem/ ├── images/ # All icons, logos, and treatment images ├── users.txt # User account and profile data ├── admins.txt # Admin credentials 
├── customers.txt # Customer treatment history ├── StartPage.java # Entry point with Login/Register ├── RegisterPage.java # User registration form 
├── LoginPage.java # User/Admin login form ├── UserDashboard.java # User profile and service access ├── AdminWindow.java # Admin control panel ├── AddCustomerForm.java
# Form for adding customers ├── TreatmentSuggestion.java # Skin tone-based suggestion logic └── Utils.java # Helper functions (file read/write, etc.)
---

## 📄 Data Format

- `users.txt`  
- `admins.txt`  
---

## 🧪 How to Run

1. Clone the repository:
 ```bash
 git clone https://github.com/theniyazkhan/parlourmanagementsystem.git
 cd parlor-management-system
- Open the project in your favorite Java IDE (e.g., IntelliJ, Eclipse).
- Make sure JDK 8 or later is installed.
- Run the file: MainMenu.java.
---
🛠 Tech Stack
Language: Java
GUI: Java Swing
Data Storage: Plain text files
Design: Custom dark mode + pastel girlish UI
Tools: ApacheNetbeans
---
🌱 Future Enhancements
Migrate to database (MySQL/SQLite).
Add email notifications.
Generate printable invoices.
Admin analytics dashboard.
Profile picture upload support.
---
🙋‍♂️ Developer
Name: Niyaz Ahmad Khan
Role: Full-stack Java Developer & UI Designer
Passion: Clean design, creative features, and user experience.
Email: ahmadkhanniyaz@gmail.com
---
📸 Screenshots

---
📜 License
This project is for educational use. Feel free to fork and enhance!
© 2025 theniyazkhan
