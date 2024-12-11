import Auth from '../components/layout/screens/auth/Auth.jsx'
import { EmailEditor } from '../components/layout/screens/email-editor/EmailEditor.jsx'
import { EmailList } from '../components/layout/screens/email-list/EmailList.jsx'
import { Home } from '../components/layout/screens/home/Home.jsx'
import Profile from '../components/layout/screens/profile/Profile.jsx'


export const routes = [
	{
		path: '/home',
		component: Home,
		isAuth: false
	},
	{
		path: '/api/auth',
		component: Auth,
		isAuth: false
	},
	{
		path: '/editor',
		component: EmailEditor,
		isAuth: true
	},
	{
		path: '/list',
		component: EmailList,
		isAuth: true
	},
	{
		path: '/profile',
		component: Profile,
		isAuth: true
	},
]