# GPLX-2025
Ứng dụng ôn thi GPLX chuẩn form đề 2025

1. Language: Kotlin
2. UI: Jetpack Compose
3. Image Loading: Coil
4. Dependency Injection: Koin DI
5. Local DB: Room
6. Api Call: Retrofit
7. Navigation: Navigation 3
8. Architecture: MVI + Clean Architecture
9. Language: Vietnamese or Vietnamese + English
10. Theme: Material theme custom


# Architecture
┌─────────────────────────────────────────────────────────────┐
│                     Presentation Layer                     │
│  ┌─────────────────┐ ┌─────────────────┐ ┌──────────────┐  │
│  │       App       │ │   Features      │ │ UI Components│  │
│  │   (MainActivity)│ │ (Onboarding,    │ │ (Design      │  │
│  │                 │ │  Recording,     │ │  System)     │  │
│  │                 │ │  Folders)       │ │              │  │
│  └─────────────────┘ └─────────────────┘ └──────────────┘  │
└─────────────────────────────────────────────────────────────┘
                                │
                                ▼
┌─────────────────────────────────────────────────────────────┐
│                     Domain Layer                           │
│  ┌─────────────────┐ ┌─────────────────┐ ┌──────────────┐  │
│  │   Use Cases     │ │   Models        │ │ Repository   │  │
│  │ (Business Logic)│ │ (Pure Kotlin)   │ │ Interfaces   │  │
│  └─────────────────┘ └─────────────────┘ └──────────────┘  │
└─────────────────────────────────────────────────────────────┘
                                │
                                ▼
┌─────────────────────────────────────────────────────────────┐
│                      Data Layer                            │
│  ┌─────────────────┐ ┌─────────────────┐ ┌──────────────┐  │
│  │   Repository    │ │   Data Sources  │ │   Database   │  │
│  │ Implementation  │ │  (Local/Remote) │ │    (Room)    │  │
│  └─────────────────┘ └─────────────────┘ └──────────────┘  │
└─────────────────────────────────────────────────────────────┘
