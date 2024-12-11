import Cookies from "js-cookie";

export default function authHeaders() {
    const user = JSON.parse(localStorage.getItem('user'));

    if (Cookies.get('UID')) {
        return { Authorization: Cookies.get('token') ? `Bearer ${Cookies.get('token')}` : '' };
    } else {
        return {};
    }
}