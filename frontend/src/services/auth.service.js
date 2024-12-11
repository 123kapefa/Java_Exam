import Cookie from 'js-cookie'
import { $axios } from '../api.js'
import { UID } from '../app.constans.js'


class AuthService {
	async main(type, email, password, login = '') {
		try {
			const payload = type === 'login'
				? { email, password }
				: { login, email, password };

			const response = await $axios.post(`auth/${type}`, payload);
			const { user, token } = response.data;

			this.setCookies(user.id, token);

			return response.data;
		} catch (error) {
			console.error('Error in AuthService.main:', error);
			throw error;
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