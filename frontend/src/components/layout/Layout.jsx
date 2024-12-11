import { useCheckToken } from '../../hooks/useCheckToken.js'
import Header from './header/Header.jsx'
import styles from './Layout.module.scss'

const Layout = ({ children }) => {
	//useCheckToken()
	
	return(
		<div className={styles.layout}>
			<Header />
			{ children }
		</div>
	)
}

export default Layout