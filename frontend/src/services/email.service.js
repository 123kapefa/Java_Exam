import { $axios } from '../api.js'

class EmailService {
    constructor() {
        this.URL = 'http://localhost:7001/api/emails';
    }

    async getEmails(id) {
        const { data } = await $axios.get(`${this.URL}/getEmails/${id}`);
        return data;
    }

    async sendEmail(userId, emailUserTo, textTitle, textContext) {
        const { data } = await $axios.post(`${this.URL}/sendEmail/${userId}/${emailUserTo}`, {
            title: textTitle,
            context: textContext
        });
        return data;
    }
}

export const emailService = new EmailService();