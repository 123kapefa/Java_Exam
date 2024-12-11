import cn from 'clsx'
import Cookies from 'js-cookie'
import styles from './ActionMenu.module.scss'
import { Link, useNavigate } from 'react-router-dom';
import { useAuth } from '../../../hooks/useAuth'
import { TOKEN } from '../../../app.constans.js'
import { menu } from './menu.data.js'

const Menu = ({ isOpen, setIsOpen }) => {
	
	const { setIsAuth } = useAuth()
	const navigate = useNavigate()

	const logoutHandler = () => {
		Cookies.remove(TOKEN)
		setIsAuth(false)
		setIsOpen(false)

		navigate('/api/auth')
	}
	
	return (
		<nav
			className={cn(styles.menu, {
				[styles.show]: isOpen
			})}
		>
			<ul>
				{menu.map((item, index) => (
					<li key={`_menu_${index}`}>
						<Link to={item.link}>{item.title}</Link>
					</li>
				))}
			</ul>
			<ul>
				<li>
					<button onClick={logoutHandler}>Logout</button>
				</li>
			</ul>
		</nav>
	)
}

export default Menu
