// FIXME Better way to pack data in graphic element
define(function (require) {

    require('./tooltip/TooltipModel');

    require('./tooltip/TooltipView');

    // Show tip controller
    /**
     * @controller
     * @property {string} type
     * @property {number} seriesIndex
     * @property {number} dataIndex
     * @property {number} [x]
     * @property {number} [y]
     */
    require('../echarts').registerAction(
        {
            type: 'showTip',
            event: 'showTip',
            update: 'none'
        },
        // noop
        function () {}
    );
    // Hide tip controller
    require('../echarts').registerAction(
        {
            type: 'hideTip',
            event: 'hideTip',
            update: 'none'
        },
        // noop
        function () {}
    );
});
