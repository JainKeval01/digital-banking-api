# 🏦 Secure Digital Banking Core REST Engine

A production-grade, secure Full-Stack Digital Banking Web Application engineered to handle safe user onboarding, real-time balance inquiries, and atomic financial transaction flows. This application is designed using a robust Three-Tier Enterprise Architecture to ensure data integrity and prevent system vulnerability exploits.

## 🚀 Core Features Engineered
- **Secure Authentication & Onboarding:** Implements an enterprise JSON-payload onboarding system to register profiles and manage login locks dynamically via browser memory vaults.
- **Atomic Money Transfers:** Features an isolated, multi-user cash transaction routine backed by strict algorithmic balance checking.
- **Real-Time Statement Ledger:** Automatically tracks and generates itemized passbook statement records on a dedicated navigation view with color-coded debit/credit flags.
- **Automatic Session Lifecycles:** Built a custom session guard wall to prevent unauthorized page skips and handle automated secure cache deletions on user logout.

## 🛠️ Technical Stack & Architecture
- **Backend Infrastructure:** Java 17, Spring Boot 3+, 
-Spring Data Jpa,
, Hibernate, Maven.
- **Database Engine:** MySQL Workbench.
- **Frontend Layer:** Semantic HTML5, Vanilla JavaScript (Asynchronous DOM Operations & Fetch API), Modern Fluid CSS Layouts (Flexbox alignment).
- **Centralized Security Layer:** `@ControllerAdvice` Global Exception Interception with explicit `@ExceptionHandler` mapping.

## 🧠 Advanced Software Engineering Patterns Applied
1. **Financial Precision (`BigDecimal`):** Eradicated native floating-point math rounding errors by enforcing `BigDecimal` objects across all monetary/ledger data columns.
2. **Input Encapsulation (DTOs & Records):** Rejected loose Request Parameters to shut down over-posting injection vulnerabilities, routing data exclusively through immutable Java `Record` structures.
3. **Data Protection Safeguards:** Integrated automated lifecycle triggers via `@PrePersist` to achieve independent, bulletproof audit tracking timestamps.
4. **Resilient System Error Management:** Implemented an abstract Global Exception Handling engine to catch missing database references cleanly, returning accurate HTTP status payloads to the UI instead of crashing the server context.


