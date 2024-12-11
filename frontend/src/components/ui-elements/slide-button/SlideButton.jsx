import cn from 'clsx'
import { useState } from 'react'

import styles from './SlideButton.module.scss'

const SlideButton = ({firstChild, secondChild, type, setType}) => {
	
	const [active, setActive] = useState(type)
	
	  const handleFirstClick = () => {
    setType('first');
		setActive('first')
    //firClickHandler();
  };
	
	  const handleSecondClick = () => {
    setType('second');
		setActive('second')
    //secClickHandler();
  };
	
 	return (
		<div className={styles.bgBlock}>
			<button
        className={cn(styles.firstButton, {
          [styles.active]: active === 'first',
        })}
        onClick={handleFirstClick}
      >
        {firstChild}
      </button>
      <button
        className={cn(styles.secondButton, {
          [styles.active]: active === 'second',
        })}
        onClick={handleSecondClick}
      >
        {secondChild}
      </button>
	  </div>
  )
}

export default SlideButton
