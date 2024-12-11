import { useLocation, useNavigate } from 'react-router-dom'
import { useAuth } from '../../../hooks/useAuth.js'
import ActionMenu from '../action-menu/ActionMenu.jsx'
import styles from './Header.module.scss'

const Header = () => {
	const { pathname } = useLocation()
	const navigate = useNavigate()
	
	const {isAuth} = useAuth()
	
	return (
		<header className={styles.header}>
			{ <ActionMenu/> }
		</header>
	)
}

export default Header