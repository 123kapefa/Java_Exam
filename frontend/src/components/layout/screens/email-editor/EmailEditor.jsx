import { useMutation, useQueryClient } from '@tanstack/react-query'
import {
    Bold,
    Eraser,
    Italic,
    Strikethrough,
    Underline
} from 'lucide-react'

import React, {useRef, useState} from 'react'
import { useAuth } from '../../../../hooks/useAuth.js'
import { emailService } from '../../../../services/email.service.js'
import Layout from '../../Layout.jsx'
import styles from './EmailEditor.module.scss'
import parse from 'html-react-parser'
import { useEditor } from './useEditor.js'

export function EmailEditor() {
    
    const { userId } = useAuth()
    
    const {
        textUserTo,
        setTextUserTo,
        textTitle,
        setTextTitle,
        textContent,
        setTextContent,
        textRef,
        applyFormat,
        updateSelection
    } = useEditor()

    const queryClient = useQueryClient()
    
    const { mutate, isPending } = useMutation({
		mutationKey: ['send email'],
		mutationFn: () => emailService.sendEmail(userId, textUserTo, textTitle, textContent),
		onSuccess() {
      setTextUserTo('')
      setTextTitle('')
			setTextContent('')
      
			queryClient.refetchQueries({ queryKey: ['email list'] })
		},
	})
    
    return (
      <Layout>
            <h1>Почта</h1>
            <div className={styles.senderBlock}>
                <p>Получатель</p>
                <textarea className={styles.editorUser} rows='1' maxLength='40' cols='10' wrap='off' spellCheck='false'onChange={e => setTextUserTo(e.target.value)} value={textUserTo} placeholder='Enter email'>
                </textarea>
            </div>
            <div className={styles.preview}>{parse(textContent)}</div>
            <div className={styles.card}>
                <textarea className={styles.editorTitle}
                    rows='1'
                    cols='10'
                    maxLength='74'
                    wrap='off'
                    spellCheck='false'
                    onChange={e => setTextTitle(e.target.value)} value={textTitle} placeholder="Enter title" >
                </textarea>
          <textarea ref={textRef}
                    className={styles.editorMassage}
                    spellCheck='false'
                    onSelect={updateSelection}
                    onChange={e => setTextContent(e.target.value)}
                    value={textContent} placeholder='Enter context'>
            {textContent}
          </textarea>
                <div className={styles.actions}>
                    <div className={styles.tools}>
                        <button onClick={() => setTextContent('')}>
                            <Eraser size={18}/>
                        </button>
                        <button onClick={() => applyFormat('bold')}>
                            <Bold size={18}/>
                        </button>
                        <button onClick={() => applyFormat('italic')}>
                            <Italic size={18}/>
                        </button>
                        <button onClick={() => applyFormat('underline')}>
                            <Underline size={18}/>
                        </button>
                        <button onClick={() => applyFormat('strikethrough')}>
                            <Strikethrough size={18}/>
                        </button>
                    </div>
                    <button disabled={isPending} onClick={() => mutate()}>Отправить</button>
                </div>
            </div>
      </Layout>
    )
}
