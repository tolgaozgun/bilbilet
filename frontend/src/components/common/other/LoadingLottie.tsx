import Lottie from 'react-lottie-player';
import loadingLottie from '../../../assets/loading_lottie.json';

const LoadingLottie = () => {
	return (
		<Lottie
			style={{ width: 600, height: 600 }}
			loop
			animationData={loadingLottie}
			play
		/>
	);
};

export default LoadingLottie;
