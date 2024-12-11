import { useQuery } from '@tanstack/react-query'
import { useAuth } from '../../../../hooks/useAuth.js'
import { userService } from '../../../../services/user.service.js'
import Layout from '../../Layout.jsx'
import styles from './Profile.module.scss'


const Profile = () => {
	
	const { isAuth, userId } = useAuth()
	
		const { data } = useQuery({
		queryKey: ['email list'],
		queryFn: () => userService.getUser(userId),
	})
	
	return (
		<div>
			<Layout>
				<h1 className={styles.header}>Ваш профиль</h1>
				{isAuth ? (
					<div>
					<div className={styles.infoBlock}>
						<p>Login:</p>
						<p>{data?.login}</p>
					</div>
					<div className={styles.infoBlock}>
						<p>Email:</p>
						<p>{data?.email}</p>
					</div>
					<div>
						<button type='submit'>Изменить пароль</button>
					</div>
				</div>
					)
					:
					(
						{ Сбой }
					)}
			</Layout>
		</div>
	)
 }
 
 export default Profile