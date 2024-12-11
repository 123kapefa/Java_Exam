import Cookies from 'js-cookie'
import { createContext, useState } from 'react'

import { TOKEN, UID } from '../app.constans.js'

export const AuthContext = createContext()

const AuthProvider = ({ children }) => {
	const [isAuth, setIsAuth] = useState(!!Cookies.get('token'))
	const [userId, setUserId] = useState(Cookies.get(UID))

	return (
		<AuthContext.Provider value={{ isAuth, setIsAuth, userId, setUserId }}>
			{children}
		</AuthContext.Provider>
	)
}

export default AuthProvider