import { Link } from 'react-router-dom'
import { useAuth } from '../../../../hooks/useAuth.js'
import SlideButton from '../../../ui-elements/slide-button/SlideButton.jsx'
import Layout from '../../Layout.jsx'
import styles from "./Home.module.scss";
import React, { useState } from 'react'

export function Home() {
	
		const { isAuth } = useAuth()
	
		const [typeMessage, setTypeMessage] = useState('in')
	
    return (
      <Layout>
      <div className={styles.home}>
				<h1>Главная страница</h1>
	      {isAuth ?
		      (
						<div>
							<p>Новые сообщения</p>
						</div>
	        )
					:
		      (
						<div className={styles.homeBlock}>
							<p>Не зарегистрирован!</p>
							<Link className={styles.btn} to={'/auth'}>Вход</Link>
						</div>
		      )
				}
			</div>
      </Layout>
      
    );
}