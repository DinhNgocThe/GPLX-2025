# GPLX-2025
Ứng dụng ôn thi GPLX chuẩn form đề 2025

1. Language: Kotlin
2. UI: Jetpack Compose
3. Image Loading: Coil
4. Dependency Injection: Koin DI
5. Local DB: Room
6. Remote DB: FireStore
7. Api Call: Retrofit
8. Navigation: Navigation 3
9. Architecture: MVI + Clean Architecture
10. Language: Vietnamese 
11. Theme: Material theme custom

# Design
- [Link Figma](https://www.figma.com/design/SAHbai6hFQs3BlWH63B8Pb/Onboarding--Login---Register---App-Ui--Community---Copy-?node-id=0-1&p=f&t=vI5TVs2SpMVxtxE5-0)

## 🏗️ Architecture Overview

```
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
```

```
Sample Flow: View ---> Intent ---> ViewModel(method processIntent()) ---> UseCase ---> Repository ---> DataSource
             View <---State; Event <--- 
```

