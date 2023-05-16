import Cookies from "js-cookie"
import { authService } from "../../services/auth"
import { useUser } from "./useUser"

export const useRefresh = () => {
    const user = useUser()

    const refresh = async () => {
        if (!user) {
            return null
        }   
        
        const res = await authService.refresh(user.refreshToken)

        if (!res) {
            return null;
        }

        Cookies.set('currentUser', JSON.stringify({
                ...user,
                refreshToken: res.refreshToken
            }))
            
        return res;
    }

    return refresh
}