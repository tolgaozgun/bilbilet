enum UserType {
    Company="Company",
    Traveler="Traveler",
}

export type User = {
    email: string
    userType: UserType
    accessToken: string
    refreshToken: string
}

