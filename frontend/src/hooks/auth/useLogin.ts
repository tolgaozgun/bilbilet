import Cookies from 'js-cookie';
import { login as loginService } from "../../services/auth";
import { User } from '../../types';

export const useLogin = () => {
    const login = async (email: string, password: string) => {
        const user = await loginService(email, password)
        if (user) {
            Cookies.set('currentUser', JSON.stringify(user))
        }

        return user as User;
    }

    return { login }
}