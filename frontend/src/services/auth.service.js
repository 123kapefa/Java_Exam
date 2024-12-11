import Cookie from 'js-cookie'
import { $axios } from '../api.js'
import { UID } from '../app.constans.js'


class AuthService {
	async main(type, email, password, login = '') {
		try {
			const payload = type === 'login'
				? { email, password }
				: { role: "user", login, user: "artur", email, password };

			const response = await $axios.post(`auth/${type}`, payload);
			const { user, token } = response.data;

			this.setCookies(user.id, token);

			return response.data;
		} catch (error) {
			console.error('Signup failed:', error.response ? error.response.data : error.message);
		}
	}

	setCookies(userId, token) {
		if (userId) {
			Cookie.set(UID, userId);
		}
		if (token) {
			Cookie.set('token', token, { secure: true, sameSite: 'strict' }); // Безопасные настройки
		}
	}
}

export default new AuthService()