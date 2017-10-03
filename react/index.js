'use strict';

import React from 'react';
import {AppRegistry, StyleSheet, View} from 'react-native';
import SomeView from "./SomeView"


class HelloWorld extends React.Component {
    render() {
        return (
            <View style={styles.container}>
                <SomeView style={styles.hello}/>
            </View>
        )
    }
}

var styles = StyleSheet.create({
    container: {
        flex: 1,
        justifyContent: 'center',
    },
    hello: {
        width: 100,
        height: 100,
        margin: 10,
    },
});

AppRegistry.registerComponent('NativeWidget', () => HelloWorld);