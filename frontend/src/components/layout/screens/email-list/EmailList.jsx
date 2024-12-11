import parse from 'html-react-parser'
import { useEffect, useState } from 'react'
import { useAuth } from '../../../../hooks/useAuth.js'
import { emailService } from '../../../../services/email.service.js'
import SlideButton from '../../../ui-elements/slide-button/SlideButton.jsx'
import Layout from '../../Layout.jsx'
import styles from './EmailList.module.scss'
import { useQuery } from '@tanstack/react-query'

export function EmailList() {
	
	const { userId } = useAuth()
	const [typeMessage, setTypeMessage] = useState('second')
	const [message, setMessage] = useState([])
	
	useEffect(() => {
		setMessage(data)
	}, [message])


	const { data } = useQuery({
		queryKey: ['email list'],
		queryFn: () => emailService.getEmails(userId),
	})
	
	return (
		<div>
			<Layout>
				<h1>Список писем</h1>
				<SlideButton
					firstChild={'Входящие'}
					secondChild={'Исходящие'}
					type={typeMessage}
					setType={setTypeMessage}/>
				<div className={styles.list}>
					{typeMessage === 'first' ? (
						<div>
							<h2>Входящие</h2>
							{message?.receivedEmails?.map(email => (
								<div className={styles.emailMessage} key={email.id}>
									<div className={styles.emailInfoUser}>
										{ "от кого: " + (email.emailFromUser.email)}
									</div>
									<div className={styles.messageTitle}>
										{parse(email.title)}
									</div>
									<div className={styles.messageContext}>
										{parse(email.context)}
									</div>
								</div>
							))}
						</div>
					)
					:
					(
						<div>
							<h2>Исходящие</h2>
							{message?.sentEmails?.map(email => (
								<div className={styles.emailMessage} key={email.id}>
									<div className={styles.emailInfoUser}>
										{ "кому: " + (email.emailToUser.email)}
									</div>
									<div className={styles.messageTitle}>
										{parse(email.title)}
									</div>
									<div className={styles.messageContext}>
										{parse(email.context)}
									</div>
								</div>
							))}
						</div>
					)}
				</div>
			</Layout>
		</div>
	)
}