'use strict';

describe('Service: OAuthWebInterceptor', function () {

  // load the service's module
  beforeEach(module('mementoUiApp'));

  // instantiate service
  var OAuthWebInterceptor;
  beforeEach(inject(function (_OAuthWebInterceptor_) {
    OAuthWebInterceptor = _OAuthWebInterceptor_;
  }));

  it('should do something', function () {
    expect(!!OAuthWebInterceptor).toBe(true);
  });

});
