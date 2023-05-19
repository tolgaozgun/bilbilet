import Cookies from "js-cookie"
import { refresh as refreshFn } from "../services/auth"
import useAxiosSecure from "./useAxiosSecure"
import { useUser } from "./useUser"

export const useRefresh = () => {
    const user = useUser()
    const axiosSecure = useAxiosSecure()

    const refresh = async () => {
        if (!user) {
            return null
        }   
        
        const res = await refreshFn(user.refreshToken, axiosSecure)

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