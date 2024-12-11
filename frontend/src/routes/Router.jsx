import NotFound from '../components/layout/screens/not-found/NotFound.jsx'
import { useAuth } from '../hooks/useAuth.js'
import {
	Route, BrowserRouter, Routes
} from 'react-router-dom'

import { routes } from './routes.data.js'

const Router = () => {
	const { isAuth } = useAuth()
	
	return (
		<BrowserRouter>
			<Routes>
				{routes.map(route => {
					if (route.isAuth && !isAuth) {
						return false
					}
					
					return (
						<Route
							key={route.path}
							path={route.path}
							element={<route.component />}
						/>
				  )
				})}
				
				<Route path='*' element={<NotFound/>}/>
			</Routes>
		</BrowserRouter>
	)
}

export default Router