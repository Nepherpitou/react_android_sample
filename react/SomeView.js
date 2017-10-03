import {requireNativeComponent} from 'react-native'

var iface = {
    name: 'SomeView'
};

const SomeView = requireNativeComponent('RCSomeView', iface);

// module.export = SomeView

export default SomeView;