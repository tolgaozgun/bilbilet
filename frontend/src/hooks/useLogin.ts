import Cookies from 'js-cookie';
import { login as loginFn } from '../services/auth';
import { User } from '../types';

export const useLogin = () => {
    const login = async (email: string, password: string) => {
        const user = await loginFn(email, password)

        if (!user) {
            return null;
        }
        
        Cookies.set('currentUser', JSON.stringify(user))
        return user as User;
    }

    return { login }
}