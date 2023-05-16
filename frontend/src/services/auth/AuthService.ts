import { AxiosInstance } from "axios";
import useAxiosSecure from "../../hooks/useAxiosSecure";
import { Tokens, User } from "../../types";

class AuthService {
    private axiosSecure: AxiosInstance;

    constructor() {
        this.axiosSecure = useAxiosSecure()
    }

    public async login(email: string, password: string): Promise<User | null> {
        const res = await this.axiosSecure.post<User>('/auth/login', {
            email,
            password
        })
        
        if (res.status !== 200) {
            return null;
        }
    
        return {
            email: res.data.email,
            userType: res.data.userType,
            accessToken: res.data.accessToken,
            refreshToken: res.data.refreshToken
        }
    }
    
    public async logout() {
        // TODO: Send logout request to API
    }
    
    public async register() {
        // TODO
    }
    
    public async refresh(refreshToken: string): Promise<Tokens | null> {
        const res = await this.axiosSecure.get<Tokens>('/auth/refresh', {
            withCredentials: true,
            headers: {
                Authorization: `Bearer ${refreshToken}`
            }
        })
        
        if (res.status !== 200 || !res.data) {
            return null;
        }
    
        return res.data;
    }
}

export const authService = new AuthService()